/**
 * 
 */

$(".controlBtn").on('click', function(){
		let gopage = $(this).data("gopage");
		
			location.href = gopage
		
})