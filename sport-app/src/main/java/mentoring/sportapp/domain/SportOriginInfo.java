package mentoring.sportapp.domain;

import java.util.Map;
import lombok.Data;

@Data
public class SportOriginInfo {

  private String id;
  private String type;
  private Map<String, Object> attributes;
}
