package uz.pdp.sololearnuzversion.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.role.RoleEnum;
import uz.pdp.sololearnuzversion.entity.user.UserEntity;
import uz.pdp.sololearnuzversion.model.receive.UserSignInReceiveModel;
import uz.pdp.sololearnuzversion.model.receive.UserSignUpReceiveModel;
import uz.pdp.sololearnuzversion.model.response.ApiResponse;
import uz.pdp.sololearnuzversion.repository.RoleRepository;
import uz.pdp.sololearnuzversion.repository.UserRepository;
import uz.pdp.sololearnuzversion.service.base.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService {


    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    public ApiResponse addUser(
            UserSignUpReceiveModel userSignUpReceiveModel
    ){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userSignUpReceiveModel.getUsername());
        if (optionalUserEntity.isPresent())
            return USER_EXIST;

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userSignUpReceiveModel.getFullName());
        userEntity.setUsername(userSignUpReceiveModel.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userSignUpReceiveModel.getPassword()));

        if (userSignUpReceiveModel.getRoleEnum() == null)
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(RoleEnum.USER)));
        else
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(userSignUpReceiveModel.getRoleEnum())));

        userRepository.save(userEntity);

        return SUCCESS;
    }

    public ApiResponse login(
            UserSignInReceiveModel userSignInReceiveModel
    ){
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUsername(userSignInReceiveModel.getUsername());

        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;

        String token = this.generateToken(optionalUserEntity.get());
        SUCCESS.setData(token);

        return SUCCESS;
    }

    private String generateToken(UserEntity userEntity) {
        try {
            return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .setSubject(userEntity.getUsername())
                    .compact();
        }catch (Exception e){
            return null;
        }
    }
}
