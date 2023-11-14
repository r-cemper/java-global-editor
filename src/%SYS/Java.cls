Class %SYS.Java
{

ClassMethod QueryF() As %String
{
#;	set:0 val=""  
#; just because COS Qualiaatry doesn't understand $QUERY wit a return value
	set res=$Query(@$ZR,1,val)
	if $d(val) set res = res_" = "_##class(%Utility).FormatString(val)
	quit res_" "
}

ClassMethod QueryR() As %String
{
#;	set:0 val=""  
#; just because COS Qualiaatry doesn't understand $QUERY wit a return value
	set %rcc=$ZR
	if %rcc'["(" set %rcc=%rcc_"("""")"
	set res=$Query(@%rcc,-1,val)
    	if $d(val) set res = res_" = "_##class(%Utility).FormatString(val)
	quit res_" "
}

ClassMethod ZKill() As %String
{
	set res=##class(%Utility).FormatString(@$ZR)
	zkill @$ZR
	quit res
}

}
