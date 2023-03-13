package com.investree.demo.model.otp;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "OTP")
public class OneTimePassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @NonNull
    private Integer oneTimePasswordCode;
    @NonNull
    private Date expires;
}
