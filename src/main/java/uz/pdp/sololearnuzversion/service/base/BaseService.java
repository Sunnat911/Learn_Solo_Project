package uz.pdp.sololearnuzversion.service.base;

import uz.pdp.sololearnuzversion.model.response.ApiResponse;

public interface BaseService {
    ApiResponse SUCCESS = new ApiResponse("muvafaqiyatli bajarildi",true,0);
    ApiResponse SUCCESS_V2 = new ApiResponse("muvafaqiyatli bajarildi",true,0);
    ApiResponse USER_EXIST = new ApiResponse("bu username allaqachon mavjud",false,-100);
    ApiResponse USER_NOT_FOUND = new ApiResponse("bu user topilmadi",false,-101);
    ApiResponse ERROR_COURSE_NOT_FOUND = new ApiResponse(" course topilmadi",false,-101);
    ApiResponse ERROR_COURSE_CHAPTER_NOT_FOUND = new ApiResponse(" course bo'lim topilmadi",false,-101);
    ApiResponse ERROR_FILE_CREATE = new ApiResponse("rasm saqlashda xatolik, base64 tekshir",false,-101);
}
