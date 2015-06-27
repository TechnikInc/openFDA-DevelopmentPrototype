package gov.fda.util;

import gov.fda.domain.CountryNameCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CountryNameCodeRefresher 
{

	private static final Logger logger = LoggerFactory.getLogger(CountryNameCodeRefresher.class);
	public static final String COUNTRY_QUERY_STRING_STD = "http://data.okfn.org/data/core/country-list/r/data.json";
	public static final String COUNTRY_QUERY_STRING_ALT = "https://github.com/krishnachaganti/technikfda/blob/master/src/main/webapp/resources/core/staticdata/countries.json";
	
	/*
	 *  This queries the countries list from standard place or alternate source and parse them to list object
	 */
	public static  List<CountryNameCode> getCountries()
	{
		RestTemplate restTemplate = new RestTemplate();
		String queryString = COUNTRY_QUERY_STRING_STD;
		logger.debug(queryString);
		List<CountryNameCode> listCountries = new ArrayList<CountryNameCode>();
		
		try{
			/* one of the website responds with application/json other one sends text/plain, 
			 * first receive as string then convert to appropriate type.
			 */
			String receivedJSONString = null;
			/*try{
				receivedJSONString = restTemplate.getForObject(queryString,  String.class);
			}catch (RestClientException re){
				re.printStackTrace();
				receivedJSONString  = getLocalCountryList();
				
			}*/
			
			receivedJSONString  = getLocalCountryList();
			
			List<LinkedHashMap<String, String>> countries = null;
		//				restTemplate.getForObject(queryString,  List.class);
		//	Map<String,String> countries = new HashMap<String,String>();
			ObjectMapper mapper = new ObjectMapper();
			try {
			    //convert JSON string to Map
				countries = mapper.readValue(receivedJSONString, List.class);
			} catch (Exception e) {
			     logger.info("Exception converting {} to map", countries, e);
			}
			
			for(LinkedHashMap<String, String>country : countries){
					String name = country.get("Name");
					String code = country.get("Code");
			       listCountries.add(new CountryNameCode(name,code));
			}
		}catch (RestClientException re){
			re.printStackTrace();
			
		}
		return listCountries;
	}
	
	private static String getLocalCountryList()
	{
		
		String localCountryDataList =
				"[   {\"Name\": \"Afghanistan\", \"Code\": \"AF\"}, {\"Name\": \"Albania\", \"Code\": \"AL\"},   {\"Name\": \"Algeria\", \"Code\": \"DZ\"}]";
//				"[   {\"Name\": \"Afghanistan\", \"Code\": \"AF\"}, {\"Name\": \"Albania\", \"Code\": \"AL\"},   {\"Name\": \"Algeria\", \"Code\": \"DZ\"},   {\"Name\": \"American Samoa\", \"Code\": \"AS\"},   {\"Name\": \"AndorrA\", \"Code\": \"AD\"},   {\"Name\": \"Angola\", \"Code\": \"AO\"},   {\"Name\": \"Anguilla\", \"Code\": \"AI\"},   {\"Name\": \"Antarctica\", \"Code\": \"AQ\"},   {\"Name\": \"Antigua and Barbuda\", \"Code\": \"AG\"},   {\"Name\": \"Argentina\", \"Code\": \"AR\"},   {\"Name\": \"Armenia\", \"Code\": \"AM\"},   {\"Name\": \"Aruba\", \"Code\": \"AW\"},   {\"Name\": \"Australia\", \"Code\": \"AU\"},   {\"Name\": \"Austria\", \"Code\": \"AT\"},   {\"Name\": \"Azerbaijan\", \"Code\": \"AZ\"},   {\"Name\": \"Bahamas\", \"Code\": \"BS\"},   {\"Name\": \"Bahrain\", \"Code\": \"BH\"},   {\"Name\": \"Bangladesh\", \"Code\": \"BD\"},   {\"Name\": \"Barbados\", \"Code\": \"BB\"},   {\"Name\": \"Belarus\", \"Code\": \"BY\"},   {\"Name\": \"Belgium\", \"Code\": \"BE\"},   {\"Name\": \"Belize\", \"Code\": \"BZ\"},   {\"Name\": \"Benin\", \"Code\": \"BJ\"},   {\"Name\": \"Bermuda\", \"Code\": \"BM\"},   {\"Name\": \"Bhutan\", \"Code\": \"BT\"},   {\"Name\": \"Bolivia\", \"Code\": \"BO\"},   {\"Name\": \"Bosnia and Herzegovina\", \"Code\": \"BA\"},   {\"Name\": \"Botswana\", \"Code\": \"BW\"},   {\"Name\": \"Bouvet Island\", \"Code\": \"BV\"},   {\"Name\": \"Brazil\", \"Code\": \"BR\"},   {\"Name\": \"British Indian Ocean Territory\", \"Code\": \"IO\"},   {\"Name\": \"Brunei Darussalam\", \"Code\": \"BN\"},   {\"Name\": \"Bulgaria\", \"Code\": \"BG\"},   {\"Name\": \"Burkina Faso\", \"Code\": \"BF\"},   {\"Name\": \"Burundi\", \"Code\": \"BI\"},   {\"Name\": \"Cambodia\", \"Code\": \"KH\"},   {\"Name\": \"Cameroon\", \"Code\": \"CM\"},   {\"Name\": \"Canada\", \"Code\": \"CA\"},   {\"Name\": \"Cape Verde\", \"Code\": \"CV\"},   {\"Name\": \"Cayman Islands\", \"Code\": \"KY\"},   {\"Name\": \"Central African Republic\", \"Code\": \"CF\"},   {\"Name\": \"Chad\", \"Code\": \"TD\"},   {\"Name\": \"Chile\", \"Code\": \"CL\"},   {\"Name\": \"China\", \"Code\": \"CN\"},   {\"Name\": \"Christmas Island\", \"Code\": \"CX\"},   {\"Name\": \"Cocos (Keeling) Islands\", \"Code\": \"CC\"},   {\"Name\": \"Colombia\", \"Code\": \"CO\"},   {\"Name\": \"Comoros\", \"Code\": \"KM\"},   {\"Name\": \"Congo\", \"Code\": \"CG\"},   {\"Name\": \"Congo, The Democratic Republic of the\", \"Code\": \"CD\"},   {\"Name\": \"Cook Islands\", \"Code\": \"CK\"},   {\"Name\": \"Costa Rica\", \"Code\": \"CR\"},   {\"Name\": \"Cote voire\", \"Code\": \"CI\"},   {\"Name\": \"Croatia\", \"Code\": \"HR\"},   {\"Name\": \"Cuba\", \"Code\": \"CU\"},   {\"Name\": \"Cyprus\", \"Code\": \"CY\"},   {\"Name\": \"Czech Republic\", \"Code\": \"CZ\"},   {\"Name\": \"Denmark\", \"Code\": \"DK\"},   {\"Name\": \"Djibouti\", \"Code\": \"DJ\"},   {\"Name\": \"Dominica\", \"Code\": \"DM\"},   {\"Name\": \"Dominican Republic\", \"Code\": \"DO\"},   {\"Name\": \"Ecuador\", \"Code\": \"EC\"},   {\"Name\": \"Egypt\", \"Code\": \"EG\"},   {\"Name\": \"El Salvador\", \"Code\": \"SV\"},   {\"Name\": \"Equatorial Guinea\", \"Code\": \"GQ\"},   {\"Name\": \"Eritrea\", \"Code\": \"ER\"},   {\"Name\": \"Estonia\", \"Code\": \"EE\"},   {\"Name\": \"Ethiopia\", \"Code\": \"ET\"},   {\"Name\": \"Falkland Islands (Malvinas)\", \"Code\": \"FK\"},   {\"Name\": \"Faroe Islands\", \"Code\": \"FO\"},   {\"Name\": \"Fiji\", \"Code\": \"FJ\"},   {\"Name\": \"Finland\", \"Code\": \"FI\"},   {\"Name\": \"France\", \"Code\": \"FR\"},   {\"Name\": \"French Guiana\", \"Code\": \"GF\"},   {\"Name\": \"French Polynesia\", \"Code\": \"PF\"},   {\"Name\": \"French Southern Territories\", \"Code\": \"TF\"},   {\"Name\": \"Gabon\", \"Code\": \"GA\"},   {\"Name\": \"Gambia\", \"Code\": \"GM\"},   {\"Name\": \"Georgia\", \"Code\": \"GE\"},   {\"Name\": \"Germany\", \"Code\": \"DE\"},   {\"Name\": \"Ghana\", \"Code\": \"GH\"},   {\"Name\": \"Gibraltar\", \"Code\": \"GI\"},   {\"Name\": \"Greece\", \"Code\": \"GR\"},   {\"Name\": \"Greenland\", \"Code\": \"GL\"},   {\"Name\": \"Grenada\", \"Code\": \"GD\"},   {\"Name\": \"Guadeloupe\", \"Code\": \"GP\"},   {\"Name\": \"Guam\", \"Code\": \"GU\"},   {\"Name\": \"Guatemala\", \"Code\": \"GT\"},   {\"Name\": \"Guernsey\", \"Code\": \"GG\"},   {\"Name\": \"Guinea\", \"Code\": \"GN\"},   {\"Name\": \"Guinea-Bissau\", \"Code\": \"GW\"},   {\"Name\": \"Guyana\", \"Code\": \"GY\"},   {\"Name\": \"Haiti\", \"Code\": \"HT\"},   {\"Name\": \"Heard Island and Mcdonald Islands\", \"Code\": \"HM\"},   {\"Name\": \"Holy See (Vatican City State)\", \"Code\": \"VA\"},   {\"Name\": \"Honduras\", \"Code\": \"HN\"},   {\"Name\": \"Hong Kong\", \"Code\": \"HK\"},   {\"Name\": \"Hungary\", \"Code\": \"HU\"},   {\"Name\": \"Iceland\", \"Code\": \"IS\"},   {\"Name\": \"India\", \"Code\": \"IN\"},   {\"Name\": \"Indonesia\", \"Code\": \"ID\"},   {\"Name\": \"Iran, Islamic Republic Of\", \"Code\": \"IR\"},   {\"Name\": \"Iraq\", \"Code\": \"IQ\"},   {\"Name\": \"Ireland\", \"Code\": \"IE\"},   {\"Name\": \"Isle of Man\", \"Code\": \"IM\"},   {\"Name\": \"Israel\", \"Code\": \"IL\"},   {\"Name\": \"Italy\", \"Code\": \"IT\"},   {\"Name\": \"Jamaica\", \"Code\": \"JM\"},   {\"Name\": \"Japan\", \"Code\": \"JP\"},   {\"Name\": \"Jersey\", \"Code\": \"JE\"},   {\"Name\": \"Jordan\", \"Code\": \"JO\"},   {\"Name\": \"Kazakhstan\", \"Code\": \"KZ\"},   {\"Name\": \"Kenya\", \"Code\": \"KE\"},   {\"Name\": \"Kiribati\", \"Code\": \"KI\"},   {\"Name\": \"Korea, Democratic PeopleS Republic of\", \"Code\": \"KP\"},   {\"Name\": \"Korea, Republic of\", \"Code\": \"KR\"},   {\"Name\": \"Kuwait\", \"Code\": \"KW\"},   {\"Name\": \"Kyrgyzstan\", \"Code\": \"KG\"},   {\"Name\": \"Lao PeopleS Democratic Republic\", \"Code\": \"LA\"},   {\"Name\": \"Latvia\", \"Code\": \"LV\"},   {\"Name\": \"Lebanon\", \"Code\": \"LB\"},   {\"Name\": \"Lesotho\", \"Code\": \"LS\"},   {\"Name\": \"Liberia\", \"Code\": \"LR\"},   {\"Name\": \"Libyan Arab Jamahiriya\", \"Code\": \"LY\"},   {\"Name\": \"Liechtenstein\", \"Code\": \"LI\"},   {\"Name\": \"Lithuania\", \"Code\": \"LT\"},   {\"Name\": \"Luxembourg\", \"Code\": \"LU\"},   {\"Name\": \"Macao\", \"Code\": \"MO\"},   {\"Name\": \"Macedonia, The Former Yugoslav Republic of\", \"Code\": \"MK\"},   {\"Name\": \"Madagascar\", \"Code\": \"MG\"},   {\"Name\": \"Malawi\", \"Code\": \"MW\"},   {\"Name\": \"Malaysia\", \"Code\": \"MY\"},   {\"Name\": \"Maldives\", \"Code\": \"MV\"},   {\"Name\": \"Mali\", \"Code\": \"ML\"},   {\"Name\": \"Malta\", \"Code\": \"MT\"},   {\"Name\": \"Marshall Islands\", \"Code\": \"MH\"},   {\"Name\": \"Martinique\", \"Code\": \"MQ\"},   {\"Name\": \"Mauritania\", \"Code\": \"MR\"},   {\"Name\": \"Mauritius\", \"Code\": \"MU\"},   {\"Name\": \"Mayotte\", \"Code\": \"YT\"},   {\"Name\": \"Mexico\", \"Code\": \"MX\"},   {\"Name\": \"Micronesia, Federated States of\", \"Code\": \"FM\"},   {\"Name\": \"Moldova, Republic of\", \"Code\": \"MD\"},   {\"Name\": \"Monaco\", \"Code\": \"MC\"},   {\"Name\": \"Mongolia\", \"Code\": \"MN\"},   {\"Name\": \"Montserrat\", \"Code\": \"MS\"},   {\"Name\": \"Morocco\", \"Code\": \"MA\"},   {\"Name\": \"Mozambique\", \"Code\": \"MZ\"},   {\"Name\": \"Myanmar\", \"Code\": \"MM\"},   {\"Name\": \"Namibia\", \"Code\": \"NA\"},   {\"Name\": \"Nauru\", \"Code\": \"NR\"},   {\"Name\": \"Nepal\", \"Code\": \"NP\"},   {\"Name\": \"Netherlands\", \"Code\": \"NL\"},   {\"Name\": \"Netherlands Antilles\", \"Code\": \"AN\"},   {\"Name\": \"New Caledonia\", \"Code\": \"NC\"},   {\"Name\": \"New Zealand\", \"Code\": \"NZ\"},   {\"Name\": \"Nicaragua\", \"Code\": \"NI\"},   {\"Name\": \"Niger\", \"Code\": \"NE\"},   {\"Name\": \"Nigeria\", \"Code\": \"NG\"},   {\"Name\": \"Niue\", \"Code\": \"NU\"},   {\"Name\": \"Norfolk Island\", \"Code\": \"NF\"},   {\"Name\": \"Northern Mariana Islands\", \"Code\": \"MP\"},   {\"Name\": \"Norway\", \"Code\": \"NO\"},   {\"Name\": \"Oman\", \"Code\": \"OM\"},   {\"Name\": \"Pakistan\", \"Code\": \"PK\"},   {\"Name\": \"Palau\", \"Code\": \"PW\"},   {\"Name\": \"Palestinian Territory, Occupied\", \"Code\": \"PS\"},   {\"Name\": \"Panama\", \"Code\": \"PA\"},   {\"Name\": \"Papua New Guinea\", \"Code\": \"PG\"},   {\"Name\": \"Paraguay\", \"Code\": \"PY\"},   {\"Name\": \"Peru\", \"Code\": \"PE\"},   {\"Name\": \"Philippines\", \"Code\": \"PH\"},   {\"Name\": \"Pitcairn\", \"Code\": \"PN\"},   {\"Name\": \"Poland\", \"Code\": \"PL\"},   {\"Name\": \"Portugal\", \"Code\": \"PT\"},   {\"Name\": \"Puerto Rico\", \"Code\": \"PR\"},   {\"Name\": \"Qatar\", \"Code\": \"QA\"},   {\"Name\": \"Reunion\", \"Code\": \"RE\"},   {\"Name\": \"Romania\", \"Code\": \"RO\"},   {\"Name\": \"Russian Federation\", \"Code\": \"RU\"},   {\"Name\": \"RWANDA\", \"Code\": \"RW\"},   {\"Name\": \"Saint Helena\", \"Code\": \"SH\"},   {\"Name\": \"Saint Kitts and Nevis\", \"Code\": \"KN\"},   {\"Name\": \"Saint Lucia\", \"Code\": \"LC\"},   {\"Name\": \"Saint Pierre and Miquelon\", \"Code\": \"PM\"},   {\"Name\": \"Saint Vincent and the Grenadines\", \"Code\": \"VC\"},   {\"Name\": \"Samoa\", \"Code\": \"WS\"},   {\"Name\": \"San Marino\", \"Code\": \"SM\"},   {\"Name\": \"Sao Tome and Principe\", \"Code\": \"ST\"},   {\"Name\": \"Saudi Arabia\", \"Code\": \"SA\"},   {\"Name\": \"Senegal\", \"Code\": \"SN\"},   {\"Name\": \"Serbia and Montenegro\", \"Code\": \"CS\"},   {\"Name\": \"Seychelles\", \"Code\": \"SC\"},   {\"Name\": \"Sierra Leone\", \"Code\": \"SL\"},   {\"Name\": \"Singapore\", \"Code\": \"SG\"},   {\"Name\": \"Slovakia\", \"Code\": \"SK\"},   {\"Name\": \"Slovenia\", \"Code\": \"SI\"},   {\"Name\": \"Solomon Islands\", \"Code\": \"SB\"},   {\"Name\": \"Somalia\", \"Code\": \"SO\"},   {\"Name\": \"South Africa\", \"Code\": \"ZA\"},   {\"Name\": \"South Georgia and the South Sandwich Islands\", \"Code\": \"GS\"},   {\"Name\": \"Spain\", \"Code\": \"ES\"},   {\"Name\": \"Sri Lanka\", \"Code\": \"LK\"},   {\"Name\": \"Sudan\", \"Code\": \"SD\"},   {\"Name\": \"Suri\"Name\"\", \"Code\": \"SR\"},   {\"Name\": \"Svalbard and Jan Mayen\", \"Code\": \"SJ\"},   {\"Name\": \"Swaziland\", \"Code\": \"SZ\"},   {\"Name\": \"Sweden\", \"Code\": \"SE\"},   {\"Name\": \"Switzerland\", \"Code\": \"CH\"},   {\"Name\": \"Syrian Arab Republic\", \"Code\": \"SY\"},   {\"Name\": \"Taiwan, Province of China\", \"Code\": \"TW\"},   {\"Name\": \"Tajikistan\", \"Code\": \"TJ\"},   {\"Name\": \"Tanzania, United Republic of\", \"Code\": \"TZ\"},   {\"Name\": \"Thailand\", \"Code\": \"TH\"},   {\"Name\": \"Timor-Leste\", \"Code\": \"TL\"},   {\"Name\": \"Togo\", \"Code\": \"TG\"},   {\"Name\": \"Tokelau\", \"Code\": \"TK\"},   {\"Name\": \"Tonga\", \"Code\": \"TO\"},   {\"Name\": \"Trinidad and Tobago\", \"Code\": \"TT\"},   {\"Name\": \"Tunisia\", \"Code\": \"TN\"},   {\"Name\": \"Turkey\", \"Code\": \"TR\"},   {\"Name\": \"Turkmenistan\", \"Code\": \"TM\"},   {\"Name\": \"Turks and Caicos Islands\", \"Code\": \"TC\"},   {\"Name\": \"Tuvalu\", \"Code\": \"TV\"},   {\"Name\": \"Uganda\", \"Code\": \"UG\"},   {\"Name\": \"Ukraine\", \"Code\": \"UA\"},   {\"Name\": \"United Arab Emirates\", \"Code\": \"AE\"},   {\"Name\": \"United Kingdom\", \"Code\": \"GB\"},   {\"Name\": \"United States\", \"Code\": \"US\"},   {\"Name\": \"United States Minor Outlying Islands\", \"Code\": \"UM\"},   {\"Name\": \"Uruguay\", \"Code\": \"UY\"},   {\"Name\": \"Uzbekistan\", \"Code\": \"UZ\"},   {\"Name\": \"Vanuatu\", \"Code\": \"VU\"},   {\"Name\": \"Venezuela\", \"Code\": \"VE\"},   {\"Name\": \"Viet Nam\", \"Code\": \"VN\"},   {\"Name\": \"Virgin Islands, British\", \"Code\": \"VG\"},   {\"Name\": \"Virgin Islands, U.S.\", \"Code\": \"VI\"},   {\"Name\": \"Wallis and Futuna\", \"Code\": \"WF\"},   {\"Name\": \"Western Sahara\", \"Code\": \"EH\"},   {\"Name\": \"Yemen\", \"Code\": \"YE\"},   {\"Name\": \"Zambia\", \"Code\": \"ZM\"},   {\"Name\": \"Zimbabwe\", \"Code\": \"ZW\"} ]";
		return localCountryDataList;
	}

}
