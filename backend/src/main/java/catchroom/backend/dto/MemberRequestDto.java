package catchroom.backend.dto;

import catchroom.backend.domain.Address;
import catchroom.backend.domain.Authority;
import catchroom.backend.domain.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private String email;
    private String password;
    private String name;
    private String number;

    private String city;
    private String district;
    private String detail;
    private String zipcode;

    public Member toMember(PasswordEncoder passwordEncoder) {
        Address address = new Address(city,district,detail,zipcode);
        Member member = new Member();
        member.setName(this.getName());
        member.setAddress(address);
        member.setEmail(this.getEmail());
        member.setPassword(passwordEncoder.encode(this.password));
        member.setNumber(this.getNumber());
        member.setAuthority(Authority.ROLE_USER);
        return member;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}