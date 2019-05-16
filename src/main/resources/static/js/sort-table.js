
var current_icon = "";
$(document).on("click",".sort-arrows",
				function() {
					
					// get Click
					var clicked_icon = $(this).attr("id");
					var clicked_aux = document.getElementById(clicked_icon);
					
					//Verify if header has change
					if (clicked_aux != current_icon) {
											
						// Verify if class has change, than set as default
						if ($(current_icon).find("i").hasClass("fa fa-fw fa-sort-up")) {
							
							$(current_icon).find("i").removeClass("fa fa-fw fa-sort-up").addClass("fa fa-fw fa-sort");

						} else if ($(current_icon).find("i").hasClass("fa fa-fw fa-sort-down")) {

							$(current_icon).find("i").removeClass("fa fa-fw fa-sort-down").addClass("fa fa-fw fa-sort");				
						}	
								
						//'up' Apply Up class 
						$(this).find("i").removeClass("fa fa-fw fa-sort").addClass("fa fa-fw fa-sort-up");

						// Att current icon
						current_icon = clicked_aux;
						
					}
												
					//Change class to Up/Down
					else {
						
						if ($(this).find("i").hasClass("fa fa-fw fa-sort")) {
							$(this).find("i").removeClass("fa fa-fw fa-sort").addClass("fa fa-fw fa-sort-up");

						} else if ($(this).find("i").hasClass("fa fa-fw fa-sort-up")) {
							$(this).find("i").removeClass("fa fa-fw fa-sort-up").addClass("fa fa-fw fa-sort-down");

						} else {
							$(this).find("i").removeClass("fa fa-fw fa-sort-down").addClass("fa fa-fw fa-sort-up");
						}
					}
				});

$(function sortTable() {

	// To sort a table, just place id= "clickToSort" in the table tag.
	$("#clickToSort").tablesorter()

});
