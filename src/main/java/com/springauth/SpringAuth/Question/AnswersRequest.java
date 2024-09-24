package com.springauth.SpringAuth.Question;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswersRequest {
	private Integer projectId;
    private Map<Integer, String> answers;
}
