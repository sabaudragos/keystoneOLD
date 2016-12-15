package keystone.mappers;

import keystone.domain.User;
import org.dozer.DozerConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends DozerConverter<User, keystone.dto.User> {
    public UserMapper(Class<User> prototypeA, Class<keystone.dto.User> prototypeB) {
        super(prototypeA, prototypeB);
    }

    @Override
    public keystone.dto.User convertTo(User userDomain, keystone.dto.User userDto) {
        if (userDomain == null) {
            return new keystone.dto.User();
        }
        if (userDomain.getName() != null) {
            userDto.setName(userDomain.getName());
        }
        if (userDomain.getEmail() != null) {
            userDto.setEmail(userDomain.getEmail());
        }
        if (userDomain.getId() != null) {
            userDto.setId(userDomain.getId());
        }
        if (userDomain.getPassword() != null) {
            userDto.setPassword(userDomain.getPassword());
        }
        return userDto;
    }

    @Override
    public User convertFrom(keystone.dto.User userDto, User userDomain) {
        if (userDto == null) {
            return new User();
        }
        if (userDomain.getName() != null) {
            userDto.setName(userDomain.getName());
        }
        if (userDomain.getId() != null) {
            userDto.setId(userDomain.getId());
        }
        if (userDomain.getEmail() != null) {
            userDto.setEmail(userDomain.getEmail());
        }
        if (userDomain.getPassword() != null) {
            userDto.setPassword(userDomain.getPassword());
        }

        return userDomain;
    }
}
