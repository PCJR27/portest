package sit.int221.sastt3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//ใช้ annotation ช่วย
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);

    }


}

// ใช้การ extend Response ที่ modifier แล้ว โดยตรง
//public class ItemNotFoundException extends ResponseStatusException {
//    public ItemNotFoundException(String message){
//        super(HttpStatus.NOT_FOUND, message);
//    }
//}
