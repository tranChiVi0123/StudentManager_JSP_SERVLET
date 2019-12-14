 function mySearchFunc(){
     var input = document.getElementById("myInput");
     var filter = input.value.toUpperCase();
     var table = document.getElementById("table");
     var tr = table.getElementsByTagName("tr");
	 var td2,td3,td4,txtValue2,txtValue3,txtValue4;
     for(var i =0;i<tr.length;i++){
         td2 = tr[i].getElementsByTagName("td")[2];
         td3 = tr[i].getElementsByTagName("td")[3];
         td4 = tr[i].getElementsByTagName("td")[4];
         if(td2 && td3 && td4){
            txtValue2 = td2.textContent || td2.innerText;
            txtValue3 = td3.textContent || td3.innerText;
            txtValue4 = td4.textContent || td4.innerText;
            
            if(txtValue2.toUpperCase().indexOf(filter)>-1 || txtValue3.toUpperCase().indexOf(filter)>-1 || txtValue4.toUpperCase().indexOf(filter)>-1 ){
                tr[i].style.display = "";
            }else{
                tr[i].style.display ="none";
            }
         }
     }
 }