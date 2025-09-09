package com.example.project200.services;

import com.example.project200.entities.Barber;
import com.example.project200.entities.MyUser;
import com.example.project200.entities.BarberShop;
import com.example.project200.entities.BarberServices;
import com.example.project200.handlers.AlreadyExistsException;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.handlers.UnauthorizedException;
import com.example.project200.handlers.UniqueFieldException;
import com.example.project200.repositories.*;
import com.example.project200.requestDto.BarberCreateReqDto;
import com.example.project200.requestDto.BarberUpdateReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.responseDto.PaginationResDto;
import com.example.project200.services.serviceInterfaces.BarberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;
    private final ModelMapper modelMapper;
    private final BarberShopRepository barbershopRepo;
    private final BarberServiceRepository barberServiceRepo;
    private final MyUserRepository myUserRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @Override
    public String addBarber(BarberCreateReqDto barberCreateReqDto) {
        BarberShop barbershop = barbershopRepo.findById(barberCreateReqDto.getBarberShopId())
                .orElseThrow(() -> new NotFoundException("BarberShop not found by ID - " + barberCreateReqDto.getBarberShopId()));
        if (myUserRepository.existsByUsername(barberCreateReqDto.getUsername())) {
            throw new AlreadyExistsException(barberCreateReqDto.getUsername() +" is already exists");
        }
        if(myUserRepository.existsByNumber(barberCreateReqDto.getNumber())){
            throw new AlreadyExistsException(barberCreateReqDto.getNumber()+" is already exists");
        }
        List<BarberServices> byBarberShopId = barberServiceRepo.findByBarberShopId(barbershop.getId()).orElseThrow();

        Barber map=new Barber();
//
        map.setBarberShop(barbershop);
//
        map.setServiceId(byBarberShopId.stream().map(BarberServices::getId).toList());
        map.setFirstName(barberCreateReqDto.getFirstName());
        map.setLastName(barberCreateReqDto.getLastName());
        map.setOnlineStatus(0);
        MyUser myUser=new MyUser();
        myUser.setUsername(barberCreateReqDto.getUsername());
        myUser.setRole("ROLE_BARBER");
        myUser.setNumber(barberCreateReqDto.getNumber());
        myUser.setPassword(passwordEncoder.encode(barberCreateReqDto.getUsername()));
        myUser.setBarber(map);
        log.info("new{}",map);
        map.setMyUser(myUser);
        log.info("after add my use {}",map);

        Barber save = barberRepository.save(map);

        barbershop.setBarberList(save.getBarberShop().getBarberList());
        myUserRepository.save(myUser);
        barbershopRepo.save(barbershop);

        return "Barber created successfully";
    }


    @Override
    @Transactional
    public String updateBarber(Long id, BarberUpdateReqDto barberUpdateReqDto, String token) {

        Barber map = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by ID - " + id));
//
        MyUser myUser = myUserRepository.findMyUserByBarberId(id);
        if(token==null || !token.equals(String.valueOf(myUser.getId()))){
            throw new UnauthorizedException("You are not authorized to update this profile.");
        }
        if (!myUser.getUsername().equals(barberUpdateReqDto.getUsername())) {
            // Check if the new value already exists
            if (myUserRepository.existsByUsername(barberUpdateReqDto.getUsername())) {
                throw new UniqueFieldException("Username is already exists.");
            }
        }

        if (!myUser.getNumber().equals(barberUpdateReqDto.getNumber())) {
            // Check if the new value already exists
            if (myUserRepository.existsByNumber(barberUpdateReqDto.getNumber())) {
                throw new UniqueFieldException("Number is already exists.");
            }
        }





        map.setFirstName(barberUpdateReqDto.getFirstName());
        map.setLastName(barberUpdateReqDto.getLastName());
        map.setServiceId(barberUpdateReqDto.getServiceId());
        myUser.setNumber(barberUpdateReqDto.getNumber());
        myUser.setPassword(passwordEncoder.encode(barberUpdateReqDto.getPassword()));
        myUser.setUsername(barberUpdateReqDto.getUsername());
        map.setMyUser(myUser);
        if(map.getMyUser().getMail()==null){
            if(myUserRepository.existsByMail(barberUpdateReqDto.getMail())){
                throw new AlreadyExistsException(barberUpdateReqDto.getMail()+ " is already exist");
            } else {
                myUser.setMail(barberUpdateReqDto.getMail());
                myUser.setIsVerified(0);
                String otp=emailService.generateOTP();
                myUser.setOtp(otp);
                myUser.setOtpExpirationTime(LocalDateTime.now().plusMinutes(5));
                barberRepository.save(map);
                myUserRepository.save(myUser);

                emailService.sendVerificationEmail(
                        myUser.getMail(),
                        "Verify Your Email",
                        "Your verification code is: " + otp
                );
                return"Barber updated successfully! Please check your email for verification code.";
            }
        }




        barberRepository.save(map);
        myUserRepository.save(myUser);


        return "Updated is successfully";
    }
    @Override
    public String deleteBarber(Long id,String token) {
        barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by ID - " + id));
        MyUser myUserByBarberId = myUserRepository.findMyUserByBarberId(id);
        if(token==null || !token.equals(String.valueOf(myUserByBarberId.getId()))){
            throw new UnauthorizedException("You are not authorized to delete this profile.");
        }
        barberRepository.deleteById(id);
        myUserRepository.deleteByBarberId(id);
        return "Deleted successfully";
    }
    @Override
    public List<BarberResDto> getAllBarber() {
        List<Barber> all = barberRepository.findAll();
        return all.stream().map(b -> modelMapper.map(b, BarberResDto.class)).toList();
    }

    @Override
    public BarberResDto getBarberById(Long id) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by ID - " + id));
//
        return modelMapper.map(barber, BarberResDto.class);
    }

    @Override
    public List<BarberResDto> getBarberByFirstName(String firstName) {
        List<Barber> barbers = barberRepository.findByFirstName(firstName)
                .orElseThrow(() -> new NotFoundException("Barber not found by firstName - " + firstName));
        return barbers.stream().map(b -> modelMapper.map(b, BarberResDto.class)).toList();
    }

    @Override
    public List<BarberResDto> getBarberByLastName(String lastName) {
        List<Barber> barbers = barberRepository.findByLastName(lastName)
                .orElseThrow(() -> new NotFoundException("Barber not found by lastname - " + lastName));
        return barbers.stream().map(b -> modelMapper.map(b, BarberResDto.class)).toList();
    }

    @Override
    public List<BarberResDto> getBarberByBarberShop(Long id){
        List<Barber> barbers = barberRepository.findBarberByBarberShopId(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by barberShop id -" + id));
        return barbers.stream().map(b->modelMapper.map(b,BarberResDto.class)).toList();
    }

    @Override
    public String getOnlineStatus(Long id) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by ID - " + id));
        return barber.getOnlineStatus() == 1 ? "Online" : "Offline";
    }
    @Override
    public String updateStatus(Long id, Integer newStatus,String token) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber not found by ID - " + id));
        MyUser myUserByBarberId = myUserRepository.findMyUserByBarberId(id);
        if(token==null || !token.equals(String.valueOf(myUserByBarberId.getId()))){
            throw new UnauthorizedException("You are not authorized to update status this profile.");
        }
        barber.setOnlineStatus(newStatus);
        barberRepository.save(barber);
        return newStatus == 1 ? "Online" : "Offline";
    }

    @Override
    public PaginationResDto getBarberByOnline(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Barber> barbersByOnlineStatus = barberRepository.findBarbersByOnlineStatus(pageable);
        List<BarberResDto> items=barbersByOnlineStatus.getContent()
                .stream()
                .map(s->modelMapper.map(s, BarberResDto.class))
                .toList();

        boolean hasNextPage=barbersByOnlineStatus.hasNext();
        Integer nextPage=hasNextPage ?barbersByOnlineStatus.getNumber() +1 : null;
        Integer previousPage=barbersByOnlineStatus.getNumber()>0?barbersByOnlineStatus.getNumber()-1:null;
        long totalItems=barbersByOnlineStatus.getTotalElements();
        return new PaginationResDto<>(items,hasNextPage,nextPage,previousPage,totalItems);


//
    }






    @Override
    public PaginationResDto searchEngine(String engine,int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Barber> barbers = barberRepository.searchByName(engine, pageable);
        List<BarberResDto> items=barbers.getContent()
                .stream()
                .map(s->modelMapper.map(s, BarberResDto.class))
                .toList();

        boolean hasNextPage=barbers.hasNext();
        Integer nextPage=hasNextPage ?barbers.getNumber() +1 : null;
        Integer previousPage=barbers.getNumber()>0?barbers.getNumber()-1:null;
        long totalItems=barbers.getTotalElements();
        return new PaginationResDto<>(items,hasNextPage,nextPage,previousPage,totalItems);







    }


}
