package mybatis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
	private Long productNo;
	private String name;
	private Integer price;
	private String image;
}
