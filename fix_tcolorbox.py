import re

with open('Android_Lab_Record.tex', 'r') as f:
    content = f.read()

old_box = r'\\begin\{tcolorbox\}\[breakable, sharp corners, boxrule=0.4pt, colback=white, width=\\linewidth\]'
new_box = r'\\begin{tcolorbox}[breakable, sharp corners, boxrule=0.4pt, toprule at break=0.4pt, bottomrule at break=0.4pt, colback=white, width=\\linewidth]'

content = re.sub(old_box, new_box, content)

with open('Android_Lab_Record.tex', 'w') as f:
    f.write(content)
