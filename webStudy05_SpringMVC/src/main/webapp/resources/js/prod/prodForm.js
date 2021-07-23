/**
 * 
 */
$.fn.othersSelect = function(param){
	let prodLgu = this;
    let prodBuyer = param.buyerTag;
    
    prodLgu.on("change", function(){
       let lgu = $(this).val();
       console.log(lgu);
       if(lgu){
          prodBuyer.find("option").hide();
          prodBuyer.find("option."+lgu).show();
         // prodBuyer.find("option:first").show();
         console.log(prodBuyer.find("option:first").text())
          
       }else{
          prodBuyer.find("option").show();
       }
    });
	
	return this;
}