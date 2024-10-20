package Geospatial;

import com.google.gson.internal.bind.util.ISO8601Utils;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;

public class GeoSpatial {
    public static void main(String args[]) {
        try (JedisPool jedisPool = new JedisPool("127.0.0.1", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.geoadd("stores2:geo",127.02985530619755, 37.49911212874, "some1");
                jedis.geoadd("stores2:geo",127.0333352287619, 37.491921163986234, "some2");

                // geo dist
                Double geodist = jedis.geodist("stores2:geo", "some1", "some2");
                System.out.println(geodist);

                // geo search
                List<GeoRadiusResponse> radiusResponseList = jedis.geosearch(
                        "stores2:geo",
                        new GeoCoordinate(127.033, 37.495),
                        1500,
                        GeoUnit.M
                );
                radiusResponseList.forEach(response-> System.out.println(response.getMemberByString()));
            }

        }
    }
}
