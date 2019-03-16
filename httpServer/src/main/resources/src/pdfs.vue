<template>
    <div>
        <div>
            <template>
                <!--iframe src="bean.pdf" style="height: 100%;width: 100%"></iframe -->
                <form action="/v1/pdfs" method="post" enctype="multipart/form-data">
                    <input type="file" name="file"/>
                    <input type="submit" value="submit"/>
                </form>
                <iframe src="/v1/pdfs/bean/view"></iframe>
            </template>
        </div>
    </div>
</template>
