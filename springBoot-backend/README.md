If there is any error like this ::::::::::::::::

    hint: Updates were rejected because the remote contains work that you do
    hint: not have locally. This is usually caused by another repository pushing
    hint: to the same ref. You may want to first integrate the remote changes
    hint: (e.g., 'git pull ...') before pushing again.
    hint: See the 'Note about fast-forwards' in 'git push --help' for details.
    
Then run the following command in the cmd ::::::::::::::::::::

    git pull origin master --allow-unrelated-histories
    
And then run the following command :::::::::::::::::::::

    git push origin master
