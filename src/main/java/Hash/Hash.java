package Hash;

import com.google.gson.internal.bind.util.ISO8601Utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Hash {
    public static void main(String args[]){
        try(JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)){
            try(Jedis jedis=jedisPool.getResource()){
//                jedis.hset("users:2:info","name","greg2");
//                HashMap<String, String> userInfo = new HashMap<>();
//                userInfo.put("email","greg2@fastcampus.co.kr");
//                userInfo.put("phone","010-XXXX-XXXX");
//                jedis.hset("users:2:info",userInfo);
//                jedis.hdel("users:2:info","phone");
//                Map<String, String> mp = jedis.hgetAll("users:2:info");
//                mp.forEach((key,value)-> System.out.println(key+" "+value));
                System.out.println(jedis.hincrBy("users:2:info","visits",1));
            }
        }
    }
}
