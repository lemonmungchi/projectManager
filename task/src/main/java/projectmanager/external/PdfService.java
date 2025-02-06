package projectmanager.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pdf ", url = "${api.url.pdf }")
public interface PdfService {
    @RequestMapping(method = RequestMethod.POST, path = "/pdfs")
    public void showPdf(@RequestBody Pdf pdf);
}
