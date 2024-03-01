package aopu.service.impl;

import aopu.mapper.AgentMapper;
import aopu.pojo.Agent;
import aopu.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentMapper agentMapper;

    @Override
    public List<Agent> findByProvinceAndCity(String province, String city) {
        return agentMapper.findByProvinceAndCity(province,city);
    }

    @Override
    public void addAgent(Agent agent) {
        if (agentMapper.findById(agent.getId()) != null){
            System.out.println("记录已存在，更新记录");
            agentMapper.updateAgent(agent);
        } else {
            System.out.println("记录不存在，添加记录");
            agentMapper.addAgent(agent);
        }
    }

    @Override
    public void deleteAgent(String id) {
        agentMapper.deleteAgent(id);
    }

    @Override
    public void updateAgent(Agent agent) {
        agentMapper.updateAgent(agent);
    }

    @Override
    public List<Agent> findByName(String findName) {
        return agentMapper.findByName(findName);
    }
}
