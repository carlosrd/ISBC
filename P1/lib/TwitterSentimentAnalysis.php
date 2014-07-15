<?php
include_once(dirname(__FILE__).'/twitter-client.php');
class TwitterSentimentAnalysis {
    
    protected $datumbox_api_key; 
    
    protected $consumer_key;
    protected $consumer_secret; 
    protected $access_key; 
    protected $access_secret; 
    

    public function __construct($datumbox_api_key, $consumer_key, $consumer_secret, $access_key, $access_secret){

        $this->consumer_key=$consumer_key;
        $this->consumer_secret=$consumer_secret;
        $this->access_key=$access_key;
        $this->access_secret=$access_secret;
    }
    
	// primero hacemos la consulta para obtener los tweets y despues los preparamos para tener los datos que nos interesan en un array por tweet
    public function sentimentAnalysis($twitterSearchParams) {
        $tweets=$this->getTweets($twitterSearchParams);
        
		return $this->findArrayTweets($tweets);
    }
    
	// utiliza la llamada "search/tweets" de la API de Twitter con el metodo GET y aplica los parametros correspondientes
    protected function getTweets($twitterSearchParams) {
        $Client = new TwitterApiClient(); //Use the TwitterAPIClient
        $Client->set_oauth ($this->consumer_key, $this->consumer_secret, $this->access_key, $this->access_secret);

        $tweets = $Client->call('search/tweets', $twitterSearchParams, 'GET' ); //call the service and get the list of tweets
		
        unset($Client);
        
        return $tweets;
    }
	
	    protected function findArrayTweets($tweets) {
 
        $results=array();
        foreach($tweets['statuses'] as $tweet) { //foreach of the tweets that we received
		
					if ($tweet['geo']==null){
					$results[]=array( //add the tweet message in the results
                        'id'=>$tweet['id_str'],
                        'user'=>$tweet['user']['name'],
						'location'=>$tweet['user']['location'],
						'geo'=>$tweet['geo'],
                        'text'=>$tweet['text'],
                        'url'=>'https://twitter.com/'.$tweet['user']['name'].'/status/'.$tweet['id_str'],
                    );
					}
					else {
					
					$results[]=array( //add the tweet message in the results
                        'id'=>$tweet['id_str'],
                        'user'=>$tweet['user']['name'],
						'location'=>$tweet['user']['location'],
						'geo'=>$tweet['geo']['coordinates'],
                        'text'=>$tweet['text'],
                        'url'=>'https://twitter.com/'.$tweet['user']['name'].'/status/'.$tweet['id_str'],
                    );
					
					}
						
            }
			
		return $results;
            
        }
        	
}

  
