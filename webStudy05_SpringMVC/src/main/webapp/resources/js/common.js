/**
 * 
 */

$(".controlBtn").on('click', function(){
		let gopage = $(this).data("gopage");
		if(gopape){
			location.href = gopage
		}
})