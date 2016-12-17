function como1(){
	return getJson();
}

function getJson(){
	var flag=0;
	var flag2=0;
	var json={};
	var comm=[];
	var meta=[];
	for(var i=50;i<500;i+=22){
		comm[flag]=i;
		flag++;
	}
	for(var j=50;j<500;j+=22){
		meta[flag2]=j;
		flag2++;
	}
	json.comms=comm;
	json.metas=meta;
	return json;
}