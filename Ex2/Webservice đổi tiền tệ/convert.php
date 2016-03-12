<?php
function currencyConvert($from,$to,$amount){
    
    $url = "http://www.google.com/finance/converter?a=$amount&from=$from&to=$to"; 

    $req = curl_init(); 
    $timeout = 0; 
    curl_setopt ($req, CURLOPT_URL, $url); 
    curl_setopt ($req, CURLOPT_RETURNTRANSFER, 1); 

    curl_setopt ($req, CURLOPT_USERAGENT,"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)"); 
    curl_setopt ($req, CURLOPT_CONNECTTIMEOUT, $timeout); 
    $rawdata = curl_exec($req); 
    curl_close($req); 
    
    return $rawdata;



} 
?>