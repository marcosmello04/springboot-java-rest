package marcosmello04.github.controllers;

import marcosmello04.github.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // Sum - http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return toDouble(n1) + toDouble(n2);
    }

    private Double toDouble(String n) throws Exception {
        if (n.matches("[-+]?[0-9]*\\.?[0-9]+"))
            return Double.parseDouble(n.replace(",", "."));
        else throw new UnsupportedMathOperationException("Numeric values only.");
            //return 0.0;
    }

    private boolean isNumeric(String n) {
        return !n.isEmpty();
    }
}
