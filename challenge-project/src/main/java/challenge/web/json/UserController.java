package challenge.web.json;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import challenge.domain.User;
import challenge.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("form")
    public void form() {
    }
    
    @RequestMapping("add")
    public String add(User user) throws Exception {
          
        userService.add(user);
        return "redirect:list";
    }
    
    @RequestMapping("delete")
    public String delete(@RequestParam("email") String email) throws Exception {
        
        int count = userService.delete(email);
        if (count == 0) {
            throw new Exception("해당 회원이 없습니다.");
        }
        return "redirect:list";
    }
    
    @RequestMapping("list{page}")
    public void list(
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="3") int pageSize,
            Map<String,Object> map) throws Exception {        
        
        map.put("list", userService.list(pageNo, pageSize));
    }
    
    @RequestMapping("update")
    public String update(User user) throws Exception {
        
        int count = userService.update(user);
        if (count == 0) {
            throw new Exception("해당 회원이 존재하지 않습니다.");
        }
        return "redirect:list";
    }
    
    @RequestMapping("{email}")
    public String view(
            @PathVariable String email,
            Map<String,Object> map) throws Exception {

        User user = userService.get(email);
        if (user == null) {
            throw new Exception("유효하지 않은 멤버 아이디입니다.");
        }
        map.put("user", user);
        return "user/view";
    }
}

//ver 53 - DAO 대신 Service 객체 사용
//ver 52 - InternalResourceViewResolver 적용
//         *.do 대신 /app/* 을 기준으로 URL 변경
//         페이지 관련 파라미터에 matrix variable 적용 
//ver 51 - Spring WebMVC 적용
//ver 49 - 요청 핸들러의 파라미터 값 자동으로 주입받기
//ver 48 - CRUD 기능을 한 클래스에 합치기
//ver 47 - 애노테이션을 적용하여 요청 핸들러 다루기
//ver 46 - 페이지 컨트롤러를 POJO를 변경
//ver 45 - 프론트 컨트롤러 적용
//ver 42 - JSP 적용
//ver 40 - CharacterEncodingFilter 필터 적용.
//         request.setCharacterEncoding("UTF-8") 제거
//ver 39 - forward 적용
//ver 38 - redirect 적용
//ver 37 - 컨트롤러를 서블릿으로 변경
//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - UserController에서 add() 메서드를 추출하여 클래스로 정의.
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - UserDao 변경 사항에 맞춰 이 클래스를 변경한다.
//ver 18 - ArrayList가 적용된 UserDao를 사용한다.
//         onUserList()에서 배열의 각 항목에 대해 null 값을 검사하는 부분을 제거한다.
//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 15 - UserDao를 생성자에서 주입 받도록 변경.
//ver 14 - UserDao를 사용하여 회원 데이터를 관리한다.
