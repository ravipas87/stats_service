package com.ravipas.statsService.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
//@Builder tried but it wont accept defaults, feels like and antipatter
@AllArgsConstructor
@NoArgsConstructor
public class UserScore  extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String userId;
    private Integer winCount=0;

    private Integer loseCount=0;

    private Integer tieCount=0;


    //keep build pattern in place
    public UserScore incrementWinCount(){
        this.winCount++;
        return this;
    }

    public UserScore incrementLoseCount(){
        this.loseCount++;
        return this;
    }

    public UserScore incrementTieCount() {
        this.tieCount++;
        return this;
    }

    public UserScore(final String userId){
        this.userId=userId;
    }
}
