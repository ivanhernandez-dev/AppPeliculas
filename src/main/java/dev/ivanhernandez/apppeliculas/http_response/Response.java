package dev.ivanhernandez.apppeliculas.http_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"total_records", "page", "page_size", "total_pages", "next", "previous", "data"})
public class Response {

    private Object data;
    @JsonProperty("total_records")
    private Integer totalRecords;
    private Integer page;
    @JsonProperty("page_size")
    private Integer pageSize;
    @JsonProperty("total_pages")
    private Integer totalPages;
    private String next;
    private String previous;

    public Response(Object data, int totalRecords, Optional<Integer> page, int pageSize) {
        this.data = data;
        this.totalRecords = totalRecords;
        page.ifPresent(integer -> buildPaginationMetaData(totalRecords, pageSize, integer));
    }

    public Response(Object data) {
        this.data = data;
    }

    private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.totalPages = totalPages;

        String baseUrl = getBaseUrl();

        if (page > 1 && totalPages > 1)
            this.previous = baseUrl + "/movies?page=" + (page - 1);

        if (page < totalPages)
            this.next = baseUrl + "/movies?page=" + (page + 1);
    }

    private String getBaseUrl() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            return sra.getRequest().getRequestURL().toString();
        }
        return "";
    }
}
