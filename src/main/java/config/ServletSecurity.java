package config;

import entity.Users;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;
import repository.UsersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServletSecurity {

    private final HashMap<Integer, Users> usersHashMap = new HashMap<>();
    private static ServletSecurity self;
    private UsersRepository usersRepository;

    private final Base64Encoder encoder = new Base64Encoder();

    private ServletSecurity(){
        usersRepository = new UsersRepository();
        List<Users> users = usersRepository.getUsers(new HashMap<>());

        for (Users u: users){
            usersHashMap.put(u.getId(), u);
        }
    }

    public static ServletSecurity getInstance(){
        return self == null? self = new ServletSecurity(): self;
    }

    public HashMap<Integer, Users> getUsersHashMap() {
        return usersHashMap;
    }

    public Users checkTheUser(String headerValue, String role){
        byte[] bytes = Base64.decode(headerValue);
        String[] userData = new String(bytes).split(":");

        // userData[0] = username       userData[1] = password

        Users entity = getUserFromMap(userData[0], userData[1]);
        System.out.println(entity.getPriority());
        if(entity != null && entity.getPriority().equals(role)){
            return entity;
        }

        return null;
    }

    private Users getUserFromMap(String username, String password){
        Set<Map.Entry<Integer, Users>> set = usersHashMap.entrySet();
        for (Map.Entry<Integer, Users> e: set){
            Integer key = e.getKey();
            Users value = e.getValue();

            if(value.getUsername().equals(username)
                    &&
                    value.getPassword().equals(password)){

                return value;
            }
        }

        return null;
    }

}

