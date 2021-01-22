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
import org.springframework.web.bind.annotation.RestController;

import eu.winwinit.bcc.constants.AuthorityRolesConstants;
import eu.winwinit.bcc.model.ArticoliRequests;
import eu.winwinit.bcc.security.JwtTokenProvider;
import eu.winwinit.bcc.service.ArticoliService;
import eu.winwinit.bcc.service.UtenteService;
import eu.winwinit.bcc.util.UtilClass;

@RestController
@RequestMapping("/api/v1")
public class ArticoliController {

	@Autowired
	JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

	@Autowired
	UtenteService utenteService;

	@Autowired
	ArticoliService articoliService;

	@RequestMapping(value = "/articoli", method = RequestMethod.GET)
	public ResponseEntity<List<ArticoliRequests>> articoliLists() {
		List<ArticoliRequests> articoliList = new ArrayList<ArticoliRequests>();
		articoliList.addAll(articoliService.findAll());
		return new ResponseEntity<>(articoliList, HttpStatus.OK);
	}

	@RequestMapping(value = "/articoli/codeid/{code}", method = RequestMethod.GET)
	ResponseEntity<?> getArticoliByCode(@PathVariable String code) {
		ArticoliRequests artreq = articoliService.findByCode(code);
		if (artreq == null) {
			return new ResponseEntity<>("articolo non trovato",HttpStatus.OK);
		}
		return new ResponseEntity<ArticoliRequests>(artreq, HttpStatus.OK);
	}

	@RequestMapping(value = "/articoli/search/{nomeProdotto}", method = RequestMethod.GET)
	ResponseEntity<?> getArticoliByNomeProdotto(@PathVariable String nomeProdotto) {
		List<ArticoliRequests> artreqs = articoliService.findByNome(nomeProdotto);
		if (artreqs == null) {
			return new ResponseEntity<>("articolo non trovato",HttpStatus.OK);
		}
		return new ResponseEntity<>(artreqs, HttpStatus.OK);
	}

	@PostMapping("/nuovoArticolo")
	public ResponseEntity<?> createArticolo(
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken,
			@RequestBody ArticoliRequests articoliRequests) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (articoliService.createArticolo(articoliRequests) != null) {
				return new ResponseEntity<>("nuovo articolo creato", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("non è stato possibile creare un nuovo articolo",HttpStatus.OK);
	}

	@PutMapping(value = "/modificaArticolo")
	public ResponseEntity<?> updateArticolo(
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken,
			@RequestBody ArticoliRequests articoliRequests) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (articoliService.updateArticolo(articoliRequests) != null) {
				return new ResponseEntity<>("articolo modificato", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("non è stato possibile modificare l'articolo",HttpStatus.OK);
	}

	@DeleteMapping("/eliminaArticolo/{code}")
	public ResponseEntity<?> deleteArticolo(
			@RequestHeader(value = AuthorityRolesConstants.HEADER_STRING) String jwtToken,
			@PathVariable String code) {
		Set<String> rolesSetString = UtilClass
				.fromGrantedAuthorityToStringSet(jwtTokenProvider.getRolesFromJWT(jwtToken));
		if (rolesSetString.contains(AuthorityRolesConstants.ROLE_USER)) {
			if (articoliService.deleteArticolo(code) != null) {
				return new ResponseEntity<>("articolo eliminato", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("non è stato possibile eliminare l'articolo",HttpStatus.OK);
	}
}