package com.microservice.sales.service;

import java.awt.Taskbar.State;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.client.EmployeeClient;
import com.microservice.sales.dto.DetailQuoteDTO;
import com.microservice.sales.dto.QuoteRequestDTO;
import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.model.quote;
import com.microservice.sales.repository.QuoteRepository;

@Service
public class QuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	public List<quote> getQuotes(){return quoteRepository.findAll();}
	public Optional<quote> getQuotesById(Long id) {return quoteRepository.findById(id);}
	
	//public quote createQuote(quote quoten) {
	//double subtotal = 0;
	//double totalDiscount = 0;
	//double totalTax = 0;
	// double totalAmount = 0;

	//if (quoten.getDetails() != null) {
	// for (var detail : quoten.getDetails()) {
	//  detail.setQuoteId(quoten);

	            // Calcular subtotal de la línea (cantidad * precio unitario)
	//  double lineSubtotal = detail.getAmount() * detail.getPrize();
	           
	            // Calcular descuento (como porcentaje del subtotal de la línea)
//   double discount = lineSubtotal * (detail.getDiscount() / 100.0); // Considera que puede ser fijo o porcentaje
	            
	            // Calcular impuesto (sobre el subtotal MENOS el descuento)
	           // double tax = (lineSubtotal - discount) * (detail.getTax() / 100.0);

	         // Calcular total de la línea
	           // double lineTotal = lineSubtotal - discount + tax;
	          //  detail.setTotal(lineTotal);

	          //  subtotal += lineSubtotal;
	          //  totalDiscount += discount;
	          //  totalTax += tax;
	           // totalAmount += lineTotal;
	        //}
	  //  }

	   // quoten.setSubtotal(subtotal);
	   // quoten.setTotalDiscount(totalDiscount);
	    //quoten.setTotalTax(totalTax);
	    //quoten.setTotalAmount(totalAmount); // Este es el total final con descuentos e impuestos

	   // return quoteRepository.save(quoten);
	//}
	
	
	public quote createQuoteFromRequest(QuoteRequestDTO request) {
	    // Validar que el empleado exista
	    var employee = employeeClient.getEmployeeById(request.getEmployeeId());
	    if (employee == null) {
	        throw new RuntimeException("Empleado no encontrado");
	    }

	    // Crear la entidad quote
	    quote quote = new quote();
	    quote.setClientId(request.getClientId());
	    quote.setEmployeeId(request.getEmployeeId());
	    quote.setTypePayment(request.getTypePayment());
	    quote.setObservation(request.getObservation());
	    quote.setIssueDate(request.getIssueDate());
	    quote.setExpirationDate(request.getExpirationDate());

	    // Inicializar acumuladores
	    double subtotal = 0;
	    double totalDiscount = 0;
	    double totalTax = 0;

	    // Lista de detalles
	    List<DetailQuote> details = new ArrayList<>();

	    for (DetailQuoteDTO dto : request.getDetails()) {
	        DetailQuote detail = new DetailQuote();
	        detail.setAmount(dto.getAmount());
	        detail.setPrize(dto.getPrize());
	        detail.setDiscount(dto.getDiscount());
	        detail.setTax(dto.getTax());

	        // Calcular total de la línea
	        double totalLine = (dto.getPrize() * dto.getAmount()) - dto.getDiscount() + dto.getTax();
	        detail.setTotal(totalLine);

	        // Acumular totales
	        subtotal += dto.getPrize() * dto.getAmount();
	        totalDiscount += dto.getDiscount();
	        totalTax += dto.getTax();

	        // Relación con la cotización
	        detail.setQuoteId(quote);

	        // Agregar a la lista
	        details.add(detail);
	    }

	    // Asignar detalles y totales a la cotización
	    quote.setDetails(details);
	    quote.setSubtotal(subtotal);
	    quote.setTotalDiscount(totalDiscount);
	    quote.setTotalTax(totalTax);
	    quote.setTotalAmount(subtotal - totalDiscount + totalTax);

	    // Guardar en la base de datos
	    return quoteRepository.save(quote);
	}


	
	public quote updateQuote(quote quoteUpdate) {
	    return quoteRepository.findById(quoteUpdate.getId()).map(existingQuote -> {
	        // Actualizar solo campos permitidos
	        existingQuote.setIssueDate(quoteUpdate.getIssueDate());
	        existingQuote.setExpirationDate(quoteUpdate.getExpirationDate());
	        existingQuote.setState(quoteUpdate.getState());
	        existingQuote.setClientId(quoteUpdate.getClientId());
	        existingQuote.setEmployeeId(quoteUpdate.getEmployeeId());
	        existingQuote.setTypePayment(quoteUpdate.getTypePayment());
	        existingQuote.setObservation(quoteUpdate.getObservation());
	        
	        // No tocar: details, subtotal, totalDiscount, totalTax, totalAmount
	        return quoteRepository.save(existingQuote);
	    }).orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
	}
	

	
	public void deleteQuote(Long id) {quoteRepository.deleteById(id);}	
	
	public List<quote> getQuotesByState(com.microservice.sales.model.State state) {
	    return quoteRepository.findByState(state);
	}


	
}
