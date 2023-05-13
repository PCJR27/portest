package sit.int221.sastt3.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import sit.int221.sastt3.DTO.PageDTO;
//import sit.int204.classicmodelsservice.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper() { }
    //เป็นการทำ SingleTon
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }
    public static ListMapper getInstance() {
        return listMapper;
    }
    public <S, T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        PageDTO<T> page = modelMapper.map(source, PageDTO.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }

//    public PageDTO toPageDTO(List response, Class<? extends List> aClass, ModelMapper modelMapper) {
//    }

//    public PageDTO<?> toPageDTO(Page<?> source, Class<T> targetClass, ModelMapper modelMapper) {
//        PageDTO<T> page = modelMapper.map(source, PageDTO.class);
//        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
//        return page;
//    }

}
