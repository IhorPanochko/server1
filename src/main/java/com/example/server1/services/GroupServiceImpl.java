package com.example.server1.services;

import com.example.server1.documents.Groups;
import com.example.server1.documents.UserGroupItem;
import com.example.server1.dto.GroupDto;
import com.example.server1.dto.LocationDto;
import com.example.server1.dto.UserDto;
import com.example.server1.dto.UserGroupDto;
import com.example.server1.repository.GroupsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupsRepository groupsRepository;
    private final BaseClient baseClient;

    public GroupServiceImpl(GroupsRepository groupsRepository,
                            BaseClient baseClient) {
        this.groupsRepository = groupsRepository;
        this.baseClient = baseClient;
    }

    @Override
    public Mono<GroupDto> getById(String id) {
        Mono<Groups> groupsMono = groupsRepository.findById(id);

        return groupsMono.map(groups -> {
            GroupDto groupDto = new GroupDto();
            groupDto.setId(groups.getId());
            groupDto.setName(groups.getName());

            Flux<UserGroupDto> map = Flux.fromIterable(groups.getUsers()).mapNotNull(userGroupItem -> {
                String userId = userGroupItem.getId();
                Long zip = userGroupItem.getZip();
                Mono<UserDto> usersByIdList = Mono.from(baseClient.findUsersByIdList(List.of(userId)));
                Mono<LocationDto> locationDtoFlux = Mono.from(baseClient.findLocationByZipList(List.of(zip)));
                Mono<UserGroupDto> userGroupDtoMono = Mono.zip(usersByIdList, locationDtoFlux).map(userDtoLocationDtoTuple2 -> {
                    UserDto userDto = userDtoLocationDtoTuple2.getT1();
                    LocationDto locationDto = userDtoLocationDtoTuple2.getT2();
                    return new UserGroupDto(userDto.getId(),userDto.getName(),locationDto.getCity());
                });

                return userGroupDtoMono.block();
            });
            List<UserGroupDto> userGroupDtos = map.collectList().block();
            groupDto.setUsers(userGroupDtos);
            return groupDto;
        });
    }

    @Override
    public void addGroups() {
        Groups groups = new Groups();
        groups.setName("group1");
        List<UserGroupItem> list = new ArrayList<>();
        UserGroupItem userGroupItem = new UserGroupItem("617a596d3fd7c5693686b902", 1L);
        UserGroupItem userGroupItem1 = new UserGroupItem("617a596d3fd7c5693686b903", 2L);
        UserGroupItem userGroupItem2 = new UserGroupItem("617a596d3fd7c5693686b904", 3L);
        UserGroupItem userGroupItem3 = new UserGroupItem("617a596d3fd7c5693686b905", 4L);
        UserGroupItem userGroupItem4 = new UserGroupItem("617a596d3fd7c5693686b906", 5L);
        list.add(userGroupItem);
        list.add(userGroupItem1);
        list.add(userGroupItem2);
        list.add(userGroupItem3);
        list.add(userGroupItem4);
        groups.setUsers(list);
        groupsRepository.save(groups).subscribe();
    }
}
