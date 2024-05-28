package application.DTO.filtersDTO;

import application.DTO.objectDTO.BuildingObjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingObjectPaginationFilter {

    private int currentPage;

    private int countOfTotalPage;

    private List<BuildingObjectDTO> objectDTOList;
}
