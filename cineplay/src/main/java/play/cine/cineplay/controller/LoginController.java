package play.cine.cineplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import play.cine.cineplay.request.LoginRequestDto;
import play.cine.cineplay.response.UsuarioResponseDto;
import play.cine.cineplay.service.LoginService;

@RestController
@RequestMapping("v1/login")
@CrossOrigin("http://localhost:5173")
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        var usuario = service.login(loginRequestDto.email(), loginRequestDto.senha());
        return ResponseEntity.ok(UsuarioResponseDto.fromEntity(usuario));
    }
}
