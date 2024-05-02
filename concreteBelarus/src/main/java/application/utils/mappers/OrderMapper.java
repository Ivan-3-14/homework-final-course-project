package application.utils.mappers;

import application.datalevel.entities.object.BuildingObject;
import application.datalevel.entities.order.Order;
import application.datalevel.entities.autotransportation.AutoTransport;
import application.datalevel.entities.concreteentities.Concrete;
import application.datalevel.entities.concreteentities.ConcreteGrade;
import application.datalevel.entities.concreteentities.Mobility;
import application.datalevel.entities.users.User;
import application.servicelevel.DTO.orderDTO.OrderDTO;
import application.servicelevel.DTO.autoDTO.AutoTransportDTO;

import java.util.HashSet;
import java.util.Set;

public class OrderMapper implements Mapper<OrderDTO, Order> {

    private final UserMapper userMapper = new UserMapper();
    private final ConcreteMapper concreteMapper = new ConcreteMapper();
    private final ConcreteGradeMapper concreteGradeMapper = new ConcreteGradeMapper();
    private final MobilityMapper mobilityMapper = new MobilityMapper();
    private final BuildingObjectMapper buildingObjectMapper = new BuildingObjectMapper();
    private final AutoTransportMapper autoTransportMapper = new AutoTransportMapper();

    @Override
    public Order dtoToEntity(OrderDTO orderDTO) {
        User user = userMapper.dtoToEntity(orderDTO.getUserDTO());
        Concrete concrete = concreteMapper.dtoToEntity(orderDTO.getConcreteDTO());
        ConcreteGrade concreteGrade = concreteGradeMapper.dtoToEntity(orderDTO.getConcreteGradeDTO());
        Mobility mobility = mobilityMapper.dtoToEntity(orderDTO.getMobilityDTO());
        BuildingObject buildingObject = buildingObjectMapper.dtoToEntity(orderDTO.getBuildingObjectDTO());
        Set<AutoTransport> autoTransportSet = new HashSet<>();
        if (orderDTO.getAutoTransportDTOSet() != null) {
            for (var s : orderDTO.getAutoTransportDTOSet()) {
                autoTransportSet.add(autoTransportMapper.dtoToEntity(s));
            }
        }

        return Order.builder()
                .id(orderDTO.getId())
                .dateOfDelivery(orderDTO.getDateOfDelivery())
                .timeOfDelivery(orderDTO.getTimeOfDelivery())
                .volumeOfConcrete(orderDTO.getVolumeOfConcrete())
                .comment(orderDTO.getComment())
                .numberOfPhone(orderDTO.getNumberOfPhone())
                .cost(orderDTO.getCost())
                .concrete(concrete)
                .concreteGrade(concreteGrade)
                .mobility(mobility)
                .buildingObject(buildingObject)
                .autoTransportSet(autoTransportSet)
                .orderTimeCreate(orderDTO.getOrderTimeCreate())
                .comment(orderDTO.getComment())
                .orderStatus(orderDTO.getOrderStatus())
                .user(user)
                .build();
    }

    @Override
    public OrderDTO entityToDTO(Order order) {
        Set<AutoTransportDTO> autoTransportSet = new HashSet<>();
        if (order.getAutoTransportSet() != null) {
        for (var s: order.getAutoTransportSet()) {
            autoTransportSet.add(autoTransportMapper.entityToDTO(s));
        }
        }
        return OrderDTO.builder()
                .id(order.getId())
                .dateOfDelivery(order.getDateOfDelivery())
                .timeOfDelivery(order.getTimeOfDelivery())
                .volumeOfConcrete(order.getVolumeOfConcrete())
                .comment(order.getComment())
                .numberOfPhone(order.getNumberOfPhone())
                .cost(order.getCost())
                .concreteDTO(concreteMapper.entityToDTO(order.getConcrete()))
                .concreteGradeDTO(concreteGradeMapper.entityToDTO(order.getConcreteGrade()))
                .mobilityDTO(mobilityMapper.entityToDTO(order.getMobility()))
                .buildingObjectDTO(buildingObjectMapper.entityToDTO(order.getBuildingObject()))
                .autoTransportDTOSet(autoTransportSet)
                .orderTimeCreate(order.getOrderTimeCreate())
                .comment(order.getComment())
                .orderStatus(order.getOrderStatus())
                .userDTO(userMapper.entityToDTO(order.getUser()))
                .build();
    }
}
