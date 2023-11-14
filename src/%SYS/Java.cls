Class %SYS.Java
{

ClassMethod QueryF() As %String
{
	set res=$Query(@$ZR,1,val)
	if $d(val) set res = res_" = "_##class(%Utility).FormatString(val)
	quit res_" "
}

ClassMethod QueryR() As %String
{
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