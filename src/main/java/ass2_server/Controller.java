package ass2_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<Bootstrap_params> give_json(@RequestBody client_req inf)
	{
		
		Logger logger = LoggerFactory.getLogger(Controller.class);
		dblayer conn = new dblayer("", "");
		return new ResponseEntity<Bootstrap_params>(conn.fetch_data(inf.getEndpoint_name()),HttpStatus.OK);
	}
	
	@RequestMapping(value="/inject", method = RequestMethod.POST)
	public ResponseEntity<Bootstrap_params> feed_values(@RequestBody injecter i)
	{
		
		Logger logger = LoggerFactory.getLogger(Controller.class);
		dblayer conn = new dblayer("", "");
		conn.insert_data(i);
		return new ResponseEntity<Bootstrap_params>(new Bootstrap_params(),HttpStatus.OK);
	}

	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResponseEntity<registratio_info> register_client(@RequestBody registratio_info i)
	{		
		Logger logger = LoggerFactory.getLogger(Controller.class);
		dblayer conn = new dblayer("","");		
		return new ResponseEntity<registratio_info>(conn.dump_into_db(i),HttpStatus.OK);
	}
	
	@RequestMapping(value="/deregister", method = RequestMethod.POST)
	public ResponseEntity<registratio_info> deregister_client(@RequestBody registratio_info i)
	{		
		Logger logger = LoggerFactory.getLogger(Controller.class);
		dblayer conn = new dblayer("","");	
		conn.delete_rec(i.getEndpoint_name());
		return new ResponseEntity<registratio_info>(i,HttpStatus.OK);
	}
}
