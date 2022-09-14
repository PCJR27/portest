const { template } = require('@babel/core')

function calculateBMI(weight, height) {
  bmi = weight/(height*height)
  return bmi

 }
 function getBMIMeaning(weight, height) {
  let BMI = calculateBMI(weight, height)
  if(BMI <18.5){
    return("Underweight")
  } else if(BMI >18.4 && BMI <25.0){
    return("Normal weight")
  }else if (BMI >25.0){
    return("Overweight")
  }else
  return("Unknown")
 }
module.exports = getBMIMeaning
