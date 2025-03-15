!#/bin/bash

# This bash script is to bundle the source code into a .zip file for the submission on D2L.

./clean.sh

# Create a new .zip file
zip A_P_LotteryPrizes.zip A_P_LotteryPrizes.java A_P_ProjectMethods.java

echo "A_P_LotteryPrizes.zip has been created successfully!"
