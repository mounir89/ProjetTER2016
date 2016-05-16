<?php
$numerodocument = ( isset($_GET['numerodocument']) )? $_GET['numerodocument'] : "";
$result = array();
$result['erreur'] = 1;
$result['lien'] = " ";


switch ($numerodocument) {
	case '23':
		$result['erreur'] = 0;
		$result['lien'] = "https://www.google.fr/?gfe_rd=cr&ei=h5wvV4qPG6uxtgenjL6YBA";
		break;
	case '29':
		$result['erreur'] = 0;
		$result['lien'] = "https://fr.wikipedia.org/wiki/Injection_de_dépendances";
		break;
	case '50':
		$result['erreur'] = 0;
		$result['lien'] = "https://fr.wikipedia.org/wiki/SOAP";
		break;
	case '60':
		$result['erreur'] = 0;
		$result['lien'] = "https://fr.wikipedia.org/wiki/Cache_web";
		break;
	default:
		break;
}
echo json_encode($result);


?>