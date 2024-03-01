package aopu.mapper;

import aopu.pojo.Agent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AgentMapper {
    List<Agent> findByProvinceAndCity(String province, String city);

    void deleteAgent(String id);

    void updateAgent(Agent agent);

    List<Agent> findByName(String findName);

    Agent findById(String id);

    void addAgent(Agent agent);
}
