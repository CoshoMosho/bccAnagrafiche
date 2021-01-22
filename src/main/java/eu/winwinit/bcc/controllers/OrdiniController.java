package eu.winwinit.bcc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.winwinit.bcc.constants.AuthorityRolesConstants;
import eu.winwinit.bcc.model.OrdiniInsert;
import eu.winwinit.bcc.model.Ordinihandler;
import eu.winwinit.bcc.security.JwtTokenProvider;
import eu.winwinit.bcc.service.OrdiniService;
import eu.winwinit.bcc.service.UtenteService;
import eu.winwinit.bcc.util.UtilClass;

@RestController
@RequestMapping("/api/v1")
public class OrdiniController {

	@Autowired
	JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

	@Autowired
	UtenteService utenteService;

	@Autowired
	OrdiniService ordiniService;

	@RequestMapping(value = "/ordini", method = RequestMethod.GET)
	public ResponseEntity<?> ordiniLists(
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		List<Ordinihandler> ordiniList = new ArrayList<Ordinihandler>();
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			ordiniList.addAll(ordiniService.findAll());
		}
		if (!ordiniList.isEmpty()) {
			return new ResponseEntity<>(ordiniList, HttpStatus.OK);
		}
		return new ResponseEntity<>("lista ordine non esistente", HttpStatus.OK);
		
	}

	@RequestMapping(value = "/ordini/codeid/{code}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrdineByCode(@PathVariable String code,
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			Ordinihandler ordine = ordiniService.findByCode(code);
			if (ordine != null) {
				return new ResponseEntity<Ordinihandler>(ordine, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("non è stato trovato l'ordine specificato", HttpStatus.OK);
	}

	@PostMapping("/nuovoOrdine")
	public ResponseEntity<?> createOrdine(@RequestBody OrdiniInsert ordineReq,
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (ordiniService.creaOrdine(ordineReq) != null) {
				return new ResponseEntity<>("nuovo ordine creato", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("non è stato possibile creare un nuovo ordine", HttpStatus.OK);
	}

	@PutMapping(value = "/modificaOrdini")
	public ResponseEntity<?> updateArticolo(
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken,
			@RequestBody Ordinihandler ordineReq) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (ordiniService.aggiornaOrdine(ordineReq) != null) {
				return new ResponseEntity<>("modifica effettuata", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("non è stata apportata alcuna modifica", HttpStatus.OK);
	}

	@DeleteMapping("/cancellaOrdini/{code}")
	public ResponseEntity<?> deleteArticolo(@PathVariable String code,
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (ordiniService.eliminaOrdine(code) != null) {
				return new ResponseEntity<>("l'ordine" + code + " è stato cancellato", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("l'ordine non è stato cancellato", HttpStatus.OK);
	}
}
