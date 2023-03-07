package com.hb.pojos;

import java.io.Serializable;

public class Question_POJO implements Serializable {
        private int id;
        private CharSequence question;
        private Boolean answer;


        public Question_POJO(CharSequence question, Boolean answer) {
                this.question = question;
                this.answer = answer;
        }

        public CharSequence getQuestion() {
                return question;
        }

        public Boolean getAnswer() {
                return answer;
        }
}
