package sit.int221.sastt3.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sastt3.DTO.*;
import sit.int221.sastt3.entities.Announcement;
import sit.int221.sastt3.services.AnnouncementService;
import sit.int221.sastt3.services.CategoryService;
import sit.int221.sastt3.utils.ListMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AnnounceController {
    @Autowired
    private AnnouncementService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @GetMapping("/announcements")
//    public List<AnnouncementDTO> getAllCustomer(){
//        List<Announcement> announcements=service.getAllAnnouncement();
//        List<AnnouncementDTO> announcementDTOList =announcements.stream().map(a-> modelMapper.map(a,AnnouncementDTO.class)).collect(Collectors.toList());
//        return announcementDTOList;
//    }

    public ResponseEntity<Object[]> getAllAnnouncements(@RequestParam(defaultValue="admin") String mode) {

        List<Announcement> announcements = service.getAllAnnouncement();

        List<Announcement> active =service.getActiveAnnouncement();
        List <Announcement> close =service.getCloseAnnouncement();
        Object[] response;

        switch (mode) {
            case "active":
                List<UserAllActiveDTO> activeDTOList = active.stream()
                        .map(a -> modelMapper.map(a, UserAllActiveDTO.class))
                        .collect(Collectors.toList());
                response = activeDTOList.toArray();
                break;
            case "close":
                List<UserAllCloseDTO> closeDTOList = close.stream()
                        .map(a -> modelMapper.map(a, UserAllCloseDTO.class))
                        .collect(Collectors.toList());
                response = closeDTOList.toArray();
                break;
            default:
                List<AnnouncementDTO> announcementDTOList = announcements.stream()
                        .map(a -> modelMapper.map(a, AnnouncementDTO.class))
                        .collect(Collectors.toList());
                response = announcementDTOList.toArray();
                break;
        }

        return ResponseEntity.ok(response);

    }

//
//    @GetMapping("/announcements")
//    public List<Announcement> getAllCustomer(){
////        List<Announcement> announcements=service.getAllAnnouncement();
////        List<AnnouncementDTO> announcementDTOList =announcements.stream().map(a-> modelMapper.map(a,AnnouncementDTO.class)).collect(Collectors.toList());
//        return service.getAllAnnouncement();
//    }

    @GetMapping("/announcements/{id}")

//    public ResponseEntity<Object> getDetail (@PathVariable  Integer id,
//                                               @RequestParam(defaultValue="admin") String mode ){
//        Announcement announcements = service.getAnnouncementById(id);
//        Object response;
//
//        switch (mode) {
//            case "active":
//               UserDetailActiveDTO activeDTOList = modelMapper.map(announcements,UserDetailActiveDTO.class);
//                response = activeDTOList;
//                break;
//            case "close":
//                UserDetailCloseDTO closeDTOList = modelMapper.map(announcements,UserDetailCloseDTO.class);
//                response = closeDTOList;
//                break;
//            case"admin":
//                DetailDTO detailDTO = modelMapper.map(announcements,DetailDTO.class);;
//                response = detailDTO;
//                break;
//            default:
//                DetailDTO defaultDTO = modelMapper.map(announcements,DetailDTO.class);;
//                response = defaultDTO;
//                break;
//        }
//
//        return ResponseEntity.ok(response);
//    }

    public DetailDTO getDetail (@PathVariable  Integer id){
        Announcement announcement=service.getAnnouncementById(id);
        DetailDTO detailDTO = modelMapper.map(announcement,DetailDTO.class);
        return detailDTO;
    }
    @PostMapping("/announcements")
    public Announcement createAnnouncement(@RequestBody AnnouncementInsert newAnnouncement) {
//        Announcement announcement=modelMapper.map(newAnnouncement,Announcement.class);
        return categoryService.addAnnouncement(newAnnouncement);
    }
    @DeleteMapping("/announcements/{id}")
    public void removeAnnouncement(@PathVariable Integer id){
        service.deleteAnnouncement(id);
    }

    @PutMapping("/announcements/{id}")
    public Announcement updateAnnouncement(@PathVariable Integer id, @RequestBody Announcement announcement ){
        return service.updateAnnouncement(id, announcement);
    }

    @GetMapping("/announcements/pages")
public PageDTO<AnnouncementDTO> getWithPage(@RequestParam(defaultValue = "admin") String mode,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "5") Integer size,
                        @RequestParam (required = false) Integer category){

       Page<Announcement> pageList =service.getWithPagination(mode,page,size,category);
       return listMapper.toPageDTO(pageList,AnnouncementDTO.class,modelMapper);
    }
    }

//    public ResponseEntity<PageDTO> getAllAnnouncements(@RequestParam(defaultValue = "admin") String mode,
//                                                       @RequestParam(defaultValue = "0") Integer page,
//                                                       @RequestParam(defaultValue = "5") Integer size,
//                                                        @RequestParam (required = false) Integer category) {
//
//        List<Announcement> announcements = service.getAllAnnouncement();
//
//        List<Announcement> active = service.getActiveAnnouncement();
//        List<Announcement> close = service.getCloseAnnouncement();
//        PageDTO pageDTO;
//        List response;
////        int totalPages = (int) Math.ceil((double) totalElements / size);
////        int startIndex = page * size;
////        int endIndex = Math.min(startIndex + size, totalElements);
//
//        switch (mode) {
//            case "active":
//                if(category!=null) {
//                    List<Announcement> cate =service.filterCategory(category);
//                    List<UserAllActiveDTO> activeCategory = cate.stream()
//                            .map(a -> modelMapper.map(a, UserAllActiveDTO.class))
//                            .collect(Collectors.toList());
//                    response = activeCategory;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,UserAllActiveDTO.class,modelMapper);
//
//                }else{
//                    List<UserAllActiveDTO> activeDTOList = active.stream()
//                            .map(a -> modelMapper.map(a, UserAllActiveDTO.class))
//                            .collect(Collectors.toList());
//                    response = activeDTOList;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,UserAllActiveDTO.class,modelMapper);
//                }
//                break;
//            case "close":
//                if (category!=null) {
//                    List<Announcement> cate =service.filterCategory(category);
//                    List<UserAllCloseDTO> closeCategory = cate.stream()
//                            .map(a -> modelMapper.map(a, UserAllCloseDTO.class))
//                            .collect(Collectors.toList());
//                    response = closeCategory;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,UserAllCloseDTO.class,modelMapper);
//
//                }else {
//                    List<UserAllCloseDTO> closeDTOList = close.stream()
//                            .map(a -> modelMapper.map(a, UserAllCloseDTO.class))
//                            .collect(Collectors.toList());
//                    response = closeDTOList;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,UserAllCloseDTO.class,modelMapper);
//                }
//                break;
//            case "admin":
//                if(category!=null){
//                List<Announcement> cate =service.filterCategory(category);
//                List<AnnouncementDTO> announcementCategory = cate.stream()
//                        .map(a -> modelMapper.map(a, AnnouncementDTO.class))
//                        .collect(Collectors.toList());
//                response = announcementCategory;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,AnnouncementDTO.class,modelMapper);
//            }else{
//                List<AnnouncementDTO> admin = announcements.stream()
//                        .map(a -> modelMapper.map(a, AnnouncementDTO.class))
//                        .collect(Collectors.toList());
//                response = admin;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,AnnouncementDTO.class,modelMapper);
//            }
//            break;
//            default:
//                if(category!=null){
//                    List<Announcement> cate =service.filterCategory(category);
//                    List<AnnouncementDTO> announcementCategory = cate.stream()
//                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
//                            .collect(Collectors.toList());
//                    response = announcementCategory;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,AnnouncementDTO.class,modelMapper);
//
//                }else{
//                    List<AnnouncementDTO> announcementDTOList = announcements.stream()
//                            .map(a -> modelMapper.map(a, AnnouncementDTO.class))
//                            .collect(Collectors.toList());
//                    response = announcementDTOList;
//                    int totalPages = (int) Math.ceil((double) response.size() / size);
//                    int startIndex = page * size;
//                    int endIndex = Math.min(startIndex + size, response.size());
//                    Page<?> pageResponse = new PageImpl<>(response.subList(startIndex,endIndex), PageRequest.of(page, size),response.size());
//                     pageDTO =listMapper.toPageDTO(pageResponse,AnnouncementDTO.class,modelMapper);
//                }
//
//                break;
//        }
////        int totalElements = response.size();
////        int totalPages = (int) Math.ceil((double) totalElements / size);
////        int startIndex = page * size;
////        int endIndex = Math.min(startIndex + size, totalElements);
////        Page<?> pageResponse = new PageImpl<>(response.subList(startIndex, endIndex), PageRequest.of(page, size), totalElements);
////        listMapper.toPageDTO(pageResponse,response.getClass(),modelMapper);
////        List<?> contentList = pageResponse.getContent();
////        List<?> contentDTOList = contentList.stream()
////                .map(content -> modelMapper.map(content, content.getClass()))
////                .collect(Collectors.toList());
////        System.out.println(contentDTOList);
////         PageDTO pageDTO =listMapper.toPageDTO(pageResponse,response.getClass(),modelMapper);
////        PageDTO<?> pageDTO =new PageDTO<>();
//
////
////        pageDTO.setContent(pageResponse.getContent().stream().toList());
////        pageDTO.setFirst(pageResponse.isFirst());
////        pageDTO.setLast(pageResponse.isLast());
////        pageDTO.setPage(pageResponse.getNumber());
////        pageDTO.setSize(pageResponse.getSize());
////        pageDTO.setTotalElements(pageResponse.getTotalElements());
////        pageDTO.setTotalPages(pageResponse.getTotalPages());
//
////        pageDTO.setContent(pageResponse.getContent());
////       PageDTO<?> pageDTO = modelMapper.map(pageResponse,PageDTO.class);
////       pageDTO.setContent(listMapper.mapList(response,response.getClass(),modelMapper));
////        System.out.println(pageResponse);
////        PageDTO pageDTO = listMapper.toPageDTO(pageResponse,response.getClass(),modelMapper);
////        System.out.println(category);
//        return ResponseEntity.ok(pageDTO);
//    }
//
////    @PutMapping("/announcements/{id}")
////    public Announcement updateAnnouncement(@RequestBody Announcement announcement, @PathVariable Integer id){
////        return service.updateAnnouncement(id, announcement);
////    }
//}
