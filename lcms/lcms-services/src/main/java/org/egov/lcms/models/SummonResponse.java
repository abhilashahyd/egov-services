package org.egov.lcms.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummonResponse {
	@JsonProperty("responseInfo")
	private ResponseInfo responseInfo = null;

	@JsonProperty("summons")
	private List<Summon> summons = null;
}