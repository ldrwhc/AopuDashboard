package aopu.service;

import aopu.pojo.Agent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代理商
 */
@Service
public interface AgentService {
    List<Agent> findByProvinceAndCity(String province, String city);

    void addAgent(Agent agent);

    void deleteAgent(String id);

    void updateAgent(Agent agent);

    List<Agent> findByName(String findName);

}
