package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/demo")
// アプリケーションパスの指定
public class MainController {
  @Autowired
  // UserRepositoryのBeanを取得
  // Springで自動生成され、データを処理するために使用
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
    // @ResponseBodyはreturnのStringがViewではなく応答だということを意味する
    // @RequestParamはGETかPOSTリクエストからのパラメータで会うことを意味する

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }
  
  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // JSONかXMLとusersをreturnとする
    return userRepository.findAll();
  }
}
