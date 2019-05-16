select ch.first_name, ch.last_name, ad.housenum, ad.street, ad.zip 
from child as ch
inner join child_address as ca
on ch.idchild = ca.idchild 
inner join addr as ad on ca.idaddr = ad.idaddr

