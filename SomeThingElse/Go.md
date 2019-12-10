#Go
- append (https://stackoverflow.com/questions/38543825/appending-one-element-to-nil-slice-increases-capacity-by-two)
- go is pass by value, if we need to change the value, we need to pass by pointer(https://goinbigdata.com/golang-pass-by-pointer-vs-pass-by-value/)
```
for i, v := range list {
    map[i] = &v
}
//all the value will be the same as the last v

for i, v := range list {
    num := v
    map[i] = &num
}
```