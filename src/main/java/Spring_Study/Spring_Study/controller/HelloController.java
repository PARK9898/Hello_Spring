package Spring_Study.Spring_Study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("hello")
	public String hello(Model model){
		model.addAttribute("data", "hello!!");
		return "hello"; // hello.html을 찾아서 저기로 가서 랜더링 ( 저 화면을 실행시키라고 해줌)
	}
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name , Model model){
		model.addAttribute("name",name); // name 이 키고 두번 째 name 이 attribute임
		return "hello-template";
	}
	@GetMapping("hello-string")
	@ResponseBody //바디부에 return 데이터를 직접 넣어주겠다는 것임
	public String helloString(@RequestParam("name") String name){
		return "hello" + name;
	}

}
