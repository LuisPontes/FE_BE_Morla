#!/bin/bash  

sudo cp -R /var/www/html/morla.site/ /var/github_rep/LuisPontes.github.io/morla.site/
sudo cp -R /var/www/html/morla.site/imgs/ /var/github_rep/LuisPontes.github.io/imgs/

d=$(date +%Y-%m-%d)
echo $d

git add .  
read -p "Commit description: " desc  
git commit -m "$d"
git push origin master